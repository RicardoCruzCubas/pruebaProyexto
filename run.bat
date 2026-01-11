@echo off
REM Script para ejecutar la aplicación Spring Boot con Swagger

echo ========================================
echo  Iniciando Avance 2 Proyecto con Swagger
echo ========================================
echo.
echo La aplicación estará disponible en:
echo   http://localhost:8080
echo.
echo Swagger UI disponible en:
echo   http://localhost:8080/swagger-ui.html
echo.
echo API Docs (OpenAPI 3.0):
echo   http://localhost:8080/v3/api-docs
echo.
echo ========================================
echo.

cd /d "%~dp0"

REM Buscar mvnw.cmd en el directorio actual
if exist mvnw.cmd (
    call mvnw.cmd spring-boot:run
) else (
    REM Si no encuentra mvnw.cmd, intenta usar mvn directamente
    mvn spring-boot:run
)

pause
