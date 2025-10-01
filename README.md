# Adicionar ao build.gradle do serviço

repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.gabrielcamposmartins:jwt-auth-lib:latest'
}
