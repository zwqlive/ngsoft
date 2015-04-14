package scala.demo.hello

/**
 * Created by will on 2015-4-8.
 */
class Man(name:String) extends Human(name) with SayJoke{
  val n:String = name;

}
