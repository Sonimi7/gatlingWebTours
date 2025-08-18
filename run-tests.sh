#!/bin/bash

# Скрипт для запуска тестов Gatling WebTours
# Использование: ./run-tests.sh [test-type]

echo "=== Gatling WebTours Performance Testing ==="
echo ""

# Проверяем, что SBT установлен
if ! command -v sbt &> /dev/null; then
    echo "❌ Ошибка: SBT не установлен. Установите SBT для продолжения."
    exit 1
fi

# Функция для запуска базового теста
run_basic_test() {
    echo "🚀 Запуск базового теста (отладка)..."
    echo "Цель: Проверить работоспособность сценария"
    echo "Длительность: ~3 минуты"
    echo ""
    sbt "gatling:testOnly webTours.WebToursSimulation"
}

# Функция для запуска теста производительности
run_performance_test() {
    echo "📈 Запуск ступенчатого теста производительности..."
    echo "Цель: Найти максимальную производительность системы"
    echo "Длительность: 20+ минут"
    echo "Метод: Ступенчатое увеличение нагрузки от 0 до 100% с шагом 10%"
    echo ""
    echo "⚠️  Внимание: Этот тест может занять более 20 минут!"
    read -p "Продолжить? (y/N): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        sbt "gatling:testOnly webTours.PerformanceTestSimulation"
    else
        echo "❌ Тест отменен"
    fi
}

# Функция для запуска теста надежности
run_reliability_test() {
    echo "🔒 Запуск теста надежности..."
    echo "Цель: Проверить стабильность системы под постоянной нагрузкой"
    echo "Длительность: 1 час"
    echo "Метод: Нагрузка 80% от максимальной производительности"
    echo ""
    echo "⚠️  Внимание: Этот тест займет 1 час!"
    read -p "Продолжить? (y/N): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]; then
        sbt "gatling:testOnly webTours.ReliabilityTestSimulation"
    else
        echo "❌ Тест отменен"
    fi
}

# Функция для показа справки
show_help() {
    echo "Использование: $0 [test-type]"
    echo ""
    echo "Доступные типы тестов:"
    echo "  basic        - Базовый тест для отладки (~3 мин)"
    echo "  performance  - Ступенчатый тест производительности (20+ мин)"
    echo "  reliability  - Тест надежности (1 час)"
    echo "  help         - Показать эту справку"
    echo ""
    echo "Примеры:"
    echo "  $0 basic"
    echo "  $0 performance"
    echo "  $0 reliability"
    echo ""
    echo "Результаты тестов сохраняются в папке target/gatling/results/"
}

# Основная логика
case "${1:-help}" in
    "basic")
        run_basic_test
        ;;
    "performance")
        run_performance_test
        ;;
    "reliability")
        run_reliability_test
        ;;
    "help"|*)
        show_help
        ;;
esac

echo ""
echo "✅ Тест завершен!"
echo "📊 Результаты доступны в папке: target/gatling/results/"
