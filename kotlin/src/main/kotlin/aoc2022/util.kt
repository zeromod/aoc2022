package aoc2022

fun loadFile(name: String): String = object {}.javaClass.classLoader.getResource(name)?.readText()!!

fun <T> checkEquals(expected: T, actual: T) = check(expected == actual) { "Expected $expected, but was $actual" }

fun printSparkles() = println("\uD83C\uDF84\uD83C\uDF1F\uD83C\uDF84\uD83C\uDF1F\uD83C\uDF84\uD83C\uDF85☃️❄️\uD83C\uDF84\uD83C\uDF1F\uD83C\uDF84\uD83C\uDF1F\uD83C\uDF84")