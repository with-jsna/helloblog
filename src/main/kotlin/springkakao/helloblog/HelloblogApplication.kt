package springkakao.helloblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication
class HelloblogApplication

fun main(args: Array<String>) {
    runApplication<HelloblogApplication>(*args)
}
