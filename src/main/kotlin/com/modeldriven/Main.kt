import com.modeldriven.sysmlv2.apiServiceTest.TestModelCommit
import com.modeldriven.sysmlv2.apiServiceTest.test1
import com.modeldriven.sysmlv2.apiServiceTest.testBase
import com.modeldriven.sysmlv2.apiServiceTest.testOpenAPI

fun main() {
    println("API to RDF Tests")
    //testBase()
    test1()
    TestModelCommit().test()

    //testOpenAPI()
}