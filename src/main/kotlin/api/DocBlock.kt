package org.example.api

/**
 * Class that represents a php doc block such as "/** @var Logger $log*/"
 * @property content the content of the doc block with "/$**" and "*$/"
 * @property contentWithoutSlashes the content of the doc block without "/$**" and "*$/"
 * @property hasVariableName true if the doc block contains a variable name, false otherwise
 *      used to determine if the doc block has standard type
 * @property isValid true if the doc block contains a valid @var tag, false otherwise
 */
class DocBlock(private val content: String) {

    private val contentWithoutSlashes = content.replace("/**", "")
        .replace("*/", "").trim()
    val hasVariableName = contentWithoutSlashes.contains("$")
    val isValid: Boolean = contentWithoutSlashes.contains("@var") &&
            contentWithoutSlashes.split(" ").size >= 2

    fun getValue(): String {
        return contentWithoutSlashes
    }

    /**
     * Returns the type name of the variable specified in the doc block.
     */
    fun getTypeNameString(): String {
        if (!contentWithoutSlashes.contains("@var"))
            throw IllegalArgumentException("DocBlock does not contain @var tag, given tag: $content")
        val s = contentWithoutSlashes.substringAfter("@var").trim()
        return s.substringBefore(" ")
    }

    /**
     * Returns the name of the variable specified in the doc block.
     * If not returns null
     */
    fun getTagName(): String? {
        if (!contentWithoutSlashes.contains("$"))
            return null
        return "$" + contentWithoutSlashes.substringAfter("$").substringBefore(" ")
    }
}