package org.example

import org.example.api.PhpDocBlock
import org.example.api.PhpVariable
import java.io.File


fun main() {
    val typeInference = TypeInference()

    val testFile = File("src/main/resources/exampleTest.txt")

    val phpDocBlock = PhpDocBlock(testFile.readText())
    val variable = PhpVariable($$"$log",phpDocBlock)
    val realType = typeInference.inferTypeFromDoc(variable)

    println(realType)
}