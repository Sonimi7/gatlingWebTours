package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 * Тест надежности на 80% от максимальной производительности
 * Длительность теста: 1 час
 * Цель: проверить стабильность системы под постоянной нагрузкой
 * 
 * Настройки основаны на результатах ступенчатого теста:
 * - Максимальная производительность: ~25 RPS (где система еще стабильна)
 * - Целевая нагрузка: 80% от 25 = 20 RPS
 * - Ожидаем стабильную работу без деградации
 */
class ReliabilityTestSimulation extends Simulation {
  
  // Настройки теста на основе результатов ступенчатого теста
  val testDuration = 1 hour
  val rampUpDuration = 2 minutes
  val maxUsersPerSec = 25 // Максимальная производительность из ступенчатого теста
  val targetLoad = (maxUsersPerSec * 0.8).toInt // 80% от максимальной нагрузки = 20 RPS
  
  setUp(
    CommonScenarioWebTours()
      .inject(
        // Плавный подъем до целевой нагрузки
        rampUsersPerSec(0).to(targetLoad).during(rampUpDuration),
        // Поддержание нагрузки в течение часа
        constantUsersPerSec(targetLoad).during(testDuration)
      )
  )
  .protocols(WebToursSimulation.httpProtocol)
  .assertions(
    // Критерии надежности на основе результатов ступенчатого теста
    global.failedRequests.percent.lt(5), // Допускаем до 5% ошибок (система показала деградацию на высоких нагрузках)
    global.responseTime.percentile(95).lt(2000), // 95% запросов должны выполняться быстрее 2 секунд
    global.responseTime.mean.lt(1500) // Среднее время отклика не должно превышать 1.5 секунды
  )
}
