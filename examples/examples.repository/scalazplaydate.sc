import scalaz.syntax.MonoidOps

object scalazplaydate {
  import scalaz._
	import Scalaz._
  
  1 |+| 2                                         //> res0: Int = 3
  def plus[A: Monoid](a: A, b: A) = {
    val m = implicitly[Monoid[A]]
    m.append(a, b)
  }                                               //> plus: [A](a: A, b: A)(implicit evidence$2: scalaz.Monoid[A])A
  plus((1, "snieg"), (2, "alfa"))                 //> res1: (Int, String) = (3,sniegalfa)

def validEmail(e: String) = e.startsWith("lukasz")//> validEmail: (e: String)Boolean

def checkEmail(email: String): Validation[String, String] = {
	if (validEmail(email))
		email.success
	else
		"Invalid email address".fail
}                                                 //> checkEmail: (email: String)scalaz.Validation[String,String]

def checkPhone(phone: String): Validation[String, String] = {
 if (phone.startsWith("111"))
 	phone.success
 else
   "Invalid phone".fail
}                                                 //> checkPhone: (phone: String)scalaz.Validation[String,String]

def validateWebForm(name: String, email: String, phone: String): Validation[String, String] =
	(checkEmail(email) |@| checkPhone(phone)) {
		_ + _
	}                                         //> validateWebForm: (name: String, email: String, phone: String)scalaz.Validati
                                                  //| on[String,String]
 validateWebForm("x", "alfa", "222")              //> res2: scalaz.Validation[String,String] = Failure(Invalid email addressInvali
                                                  //| d phone)

def validateWebForm2(name: String, email: String, phone: String): Validation[NonEmptyList[String], String] = {
	(checkEmail(email).toValidationNel |@| checkPhone(phone).toValidationNel) {
	  _ + _
	}
}                                                 //> validateWebForm2: (name: String, email: String, phone: String)scalaz.Validat
                                                  //| ion[scalaz.NonEmptyList[String],String]
   
  validateWebForm2("x", "alfa", "222")            //> res3: scalaz.Validation[scalaz.NonEmptyList[String],String] = Failure(NonEm
                                                  //| ptyList(Invalid email address, Invalid phone))
  val res = (((_: Int) + 1) |@| ((_: Int) * 2)) { _ |+| _ }
                                                  //> res  : Int => Int = <function1>
  res(1)                                          //> res4: Int = 4
  res(2)                                          //> res5: Int = 7
  res(3)                                          //> res6: Int = 10
  res(4)                                          //> res7: Int = 13
}