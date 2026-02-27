package org.example.api

class DocBlock(private val content: String) {

    private val contentWithoutSlashes = content.replace("/**", "")
        .replace("*/", "").trim()
    val hasVariableName = contentWithoutSlashes.contains("$")
    val isValid: Boolean = contentWithoutSlashes.contains("@var") &&
            contentWithoutSlashes.split(" ").size >= 2

    fun getValue(): String {
        return contentWithoutSlashes
    }

    fun getTypeNameString(): String {
        if (!contentWithoutSlashes.contains("@var"))
            throw IllegalArgumentException("DocBlock does not contain @var tag, given tag: $content")
        val s = contentWithoutSlashes.substringAfter("@var")
        return s.substringBefore(" ")
    }

    fun getTagName(): String {
        if (!contentWithoutSlashes.contains("$"))
            return "$${getTypeNameString()}"
        return "$" + contentWithoutSlashes.substringAfter("$").substringBefore(" ")
    }
}