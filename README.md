# Gatling WebTours Performance Testing

## Описание проекта

Проект содержит скрипты для нагрузочного тестирования веб-приложения WebTours с использованием Gatling.

## Сценарий тестирования

Скрипт выполняет следующие действия:
1. Открытие страницы http://webtours.load-test.ru:1080/webtours
2. Вход в систему под пользователем
3. Переход на страницу «Flights», выбор случайного города отправления и прибытия
4. Выбор случайного рейса по данному направлению из предложенных
5. Покупка билета
6. Переход на корневую страницу

## Структура проекта

```
src/test/scala/webTours/
├── ActionsWebTours.scala           # Действия пользователя
├── CommonScenarioWebTours.scala    # Основной сценарий
├── WebToursSimulations.scala       # Базовая симуляция
├── PerformanceTestSimulation.scala # Ступенчатый тест производительности
└── ReliabilityTestSimulation.scala # Тест надежности
```

## Запуск тестов

### 1. Базовый тест (для отладки)
```bash
sbt "gatling:testOnly webTours.WebToursSimulation"
```

### 2. Ступенчатый тест производительности
**Цель**: Найти максимальную производительность системы
**Длительность**: 20+ минут
**Метод**: Ступенчатое увеличение нагрузки от 0 до 100% с шагом 10%

```bash
sbt "gatling:testOnly webTours.PerformanceTestSimulation"
```

### 3. Тест надежности
**Цель**: Проверить стабильность системы под постоянной нагрузкой
**Длительность**: 1 час
**Метод**: Нагрузка 80% от максимальной производительности

```bash
sbt "gatling:testOnly webTours.ReliabilityTestSimulation"
```

## Настройка пользователей

Пользователи настраиваются в файле `src/test/resources/users.csv`:

```csv
firstName,lastName,address1,address2,pass1,creditCard,expDate,username
Sofia,Efimova,Mirskoy proezd,Moscow,Sofia Efimova,1111,02/25,sofi
```

**Важно**: Замените данные на ваши персональные данные, зарегистрированные на сайте.

## Результаты тестов

Результаты сохраняются в папке `target/gatling/results/` в формате HTML-отчетов.

### Анализ результатов

1. **PerformanceTestSimulation**: 
   - Найдите точку деградации системы
   - Определите максимальную производительность
   - Обновите значение `maxUsersPerSec` в `ReliabilityTestSimulation.scala`

2. **ReliabilityTestSimulation**:
   - Проверьте стабильность системы
   - Анализируйте процент ошибок и время отклика

## Требования к системе

- Java 8+
- SBT
- Доступ к http://webtours.load-test.ru:1080/webtours

## Примечания

- Перед запуском тестов убедитесь, что у вас есть зарегистрированный пользователь на сайте
- Обновите данные пользователя в `users.csv`
- Настройте параметры нагрузки в симуляциях в соответствии с возможностями тестовой среды
