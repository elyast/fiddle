object monoid {
  import scalaz._
  import scalaz.std.anyVal._
  import scalaz.std.option._
  import scalaz.syntax.std.option._
	import scalaz.std.string._
	import scalaz.std.list._
	import scalaz.std.tuple._
	import scalaz.syntax.traverse._
	import scalaz.syntax.monoid._
	
	case class Person(name: String, salary: Double)
	  
  def xget(opt: Option[Int]) = opt | Int.MaxValue //> xget: (opt: Option[Int])Int
  def zget(opt: Option[Int]) = ~opt               //> zget: (opt: Option[Int])Int
  zget(Some(42))                                  //> res0: Int = 42
  zget(None)                                      //> res1: Int = 0
  xget(some(32))                                  //> res2: Int = 32
  xget(none)                                      //> res3: Int = 2147483647
  def mzget[A : Monoid](opt: Option[A]) = ~opt    //> mzget: [A](opt: Option[A])(implicit evidence$1: scalaz.Monoid[A])A
  mzget(none[String])                             //> res4: String = ""
  1 |+| 2                                         //> res5: Int = 3
  
  List(some(42), none, some(51)).concatenate      //> res6: Option[Int] = Some(93)
  
  List(1, 2) |+| List(3, 4)                       //> res7: List[Int] = List(1, 2, 3, 4)
  
  (1, "alfa") |+| (2, "omega")                    //> res8: (Int, String) = (3,alfaomega)
  
  implicit val toPersonMonoid = new Monoid[Person] {
    def append(f1: Person, f2: => Person) = {
    	Person(f1.name, f1.salary |+| f2.salary)
    }
  	def zero = Person("John Doe", 0)
  }                                               //> toPersonMonoid  : scalaz.Monoid[monoid.Person] = monoid$$anonfun$main$1$$ano
                                                  //| n$1@1726a4a2
  Person("John", 200) |+| Person("John", 300)     //> res9: monoid.Person = Person(John,500.0)
	some(20) |+| none |+| some(22)            //> res10: Option[Int] = Some(42)
}