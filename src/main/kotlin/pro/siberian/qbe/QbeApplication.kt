package pro.siberian.qbe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QbeApplication

fun main(args: Array<String>) {
    runApplication<QbeApplication>(*args)
}
