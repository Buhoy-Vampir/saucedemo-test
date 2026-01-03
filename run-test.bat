@echo off
echo ==========================================
echo Запуск тестов SauceDemo с Allure отчетами
echo ==========================================

echo 1. Очистка проекта...
call mvn clean

echo 2. Запуск тестов...
call mvn test

echo 3. Запуск Allure сервера...
echo Отчет будет открыт в браузере автоматически
call mvn allure:serve

pause