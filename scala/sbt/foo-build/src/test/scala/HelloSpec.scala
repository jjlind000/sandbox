import org.scalatest._

class HelloSpec extends FunSuite with DiagrammedAssertions{
  test("hello should start with an H"){
    assert("hello".startsWith("H"))
  }  
}
