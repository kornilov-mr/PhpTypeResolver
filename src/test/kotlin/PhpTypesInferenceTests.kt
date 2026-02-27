import org.example.TypeInference
import org.example.api.PhpDocBlock
import org.example.api.PhpVariable
import org.example.api.types.TypeFactory
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class PhpTypesInferenceTests {
    private val typeInference = TypeInference()
    private val typeFactory = TypeFactory()

    private val standardTypeTestFile = File("src/test/resources/PhpTypeInferenceTests/standardTypeTest.txt")
    private val unionTypeTestFile = File("src/test/resources/PhpTypeInferenceTests/unionTypeTest.txt")
    private val namedTypeTestFile = File("src/test/resources/PhpTypeInferenceTests/namedTypeTest.txt")
    private val nameMismatchTestFile = File("src/test/resources/PhpTypeInferenceTests/nameMismatchTest.txt")
    private val multipleTagsTestFile = File("src/test/resources/PhpTypeInferenceTests/multipleTagTest.txt")
    private val noBlockTestFile = File("src/test/resources/PhpTypeInferenceTests/noBlockTest.txt")

    @Test
    fun standardTypeInferenceTest() {
        val phpDocBlock = PhpDocBlock(standardTypeTestFile.readText())
        val variable = PhpVariable($$"$user", phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createType("User")
        assertEquals(expectedType, realType)
    }
    @Test
    fun unionTypeInferenceTest() {
        val phpDocBlock = PhpDocBlock(unionTypeTestFile.readText())
        val variable = PhpVariable($$"$user",phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createUnionType(listOf("string","int"))
        assertEquals(expectedType, realType)
    }
    @Test
    fun namedTypeInferenceTest() {
        val phpDocBlock = PhpDocBlock(namedTypeTestFile.readText())
        val variable = PhpVariable($$"$log",phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createType("Logger")
        assertEquals(expectedType, realType)
    }
    @Test
    fun nameMismatchTypeInferenceTest() {
        val phpDocBlock = PhpDocBlock(nameMismatchTestFile.readText())
        val variable = PhpVariable($$"$guest",phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createType("mixed")
        assertEquals(expectedType, realType)
    }
    @Test
    fun multipleTagsInferenceTest() {
        val phpDocBlock = PhpDocBlock(multipleTagsTestFile.readText())
        val variable = PhpVariable($$"$name",phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createType("string")
        assertEquals(expectedType, realType)
    }
    @Test
    fun noDocBlockInferenceTest() {
        val phpDocBlock = PhpDocBlock(noBlockTestFile.readText())
        val variable = PhpVariable($$"$name",phpDocBlock)
        val realType = typeInference.inferTypeFromDoc(variable)

        val expectedType = typeFactory.createType("mixed")
        assertEquals(expectedType, realType)
    }
}