package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
 * Ступенчатый тест производительности от 0 до 100% с шагом 10%
 * Длительность теста: 20+ минут (10 шагов по 2 минуты)
 * Цель: найти максимальную производительность системы
 * 
 * Настройки основаны на результатах предыдущего ступенчатого теста:
 * - Система деградировала на ~60 RPS (75.91% ошибок)
 * - Успешная пропускная способность: ~60 RPS
 * - Максимальная нагрузка установлена на 40 RPS для поиска стабильной точки
 */
class PerformanceTestSimulation extends Simulation {
  
  // Настройки теста на основе результатов предыдущего ступенчатого теста
  val stepDuration = 2 minutes
  val maxUsersPerSec = 40 // Снижаем с 60 до 40, так как система деградировала на ~60 RPS
  
  setUp(
    CommonScenarioWebTours()
      .inject(
        // Ступенчатое увеличение нагрузки от 0 до 100%
        incrementUsersPerSec(maxUsersPerSec / 10) // Шаг 10% от максимума
          .times(10) // 10 шагов = 100%
          .eachLevelLasting(stepDuration)
          .startingFrom(0)
      )
  )
  .protocols(WebToursSimulation.httpProtocol)
  .assertions(
    // Критерии качества для поиска максимальной производительности
    global.failedRequests.percent.lt(5), // Допускаем до 5% ошибок для поиска границы
    global.responseTime.percentile(95).lt(2000), // Допускаем до 2 секунд для p95
    global.responseTime.mean.lt(1000) // Допускаем до 1 секунды среднего времени
  )
}
