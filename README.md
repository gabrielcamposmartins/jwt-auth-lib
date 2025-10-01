# Adicionar ao build.gradle do serviço

repositories {
    maven {
        url = uri("https://maven.pkg.github.com/gabrielcamposmartins/jwt-auth-lib")
        credentials {
            username = 'gabrielcamposmartins'
            password = 'SEU_TOKEN_GITHUB'
        }
    }
}

dependencies {
    implementation 'gabrielcamposmartins:jwt-security-lib:1.0.0'
}
