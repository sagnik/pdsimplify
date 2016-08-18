/**
  * Created by schoudhury on 8/18/16.
  */

object TestRectangleHelper extends FunSuite {


  val a = Rectangle(
    x1 = 0, y1 = 10,
    x2 = 10, y2 = 0
  )

  val centralRect = Rectangle(
    x1 = 50, y1 = 100,
    x2 = 100, y2 = 50
  )

  test("DSL works, non-overlapping") {
    val b = Rectangle(
      x1 = 20, y1 = 30,
      x2 = 30, y2 = 20
    )
    assert(a isLeft b)
    assert(b isRight a)
    assert(a isBelow b)
    assert(b isAbove a)
    // this check is to assert that a & b don't overlap
    assert((a isRight b) || (a isLeft b) || (a isBelow b) || (a isAbove b))
  }

  test("DSL works, overlapping") {
    val b = Rectangle(
      x1 = 9, y1 = 30,
      x2 = 30, y2 = 9
    )
    assert(!(a isLeft b))
    assert(!(b isRight a))
    assert(!(a isBelow b))
    assert(!(b isAbove a))
    // this check is to assert that a & b overlap
    assert(!(a isRight b) && !(a isLeft b) && !(a isBelow b) && !(a isAbove b))
  }

  test("DSL distance, non spanning, non intersecting, bottom left") {

    val r = Rectangle(
      x1 = 0, y1 = 10,
      x2 = 10, y2 = 0
    )
    assert((centralRect distance r) == 80f)
    assert((r distance centralRect) == 80f)
  }

  test("DSL distance, non spanning, non intersecting, bottom") {

    val r = Rectangle(
      x1 = 50, y1 = 10,
      x2 = 100, y2 = 0
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

  test("DSL distance, non spanning, non intersecting, bottom right") {

    val r = Rectangle(
      x1 = 140, y1 = 10,
      x2 = 150, y2 = 0
    )

    assert((centralRect distance r) == 80f)
    assert((r distance centralRect) == 80f)
  }

  test("DSL distance, non spanning, non intersecting, right") {

    val r = Rectangle(
      x1 = 140, y1 = 100,
      x2 = 150, y2 = 50
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

  test("DSL distance, non spanning, non intersecting, right top") {

    val r = Rectangle(
      x1 = 140, y1 = 150,
      x2 = 150, y2 = 140
    )

    assert((centralRect distance r) == 80f)
    assert((r distance centralRect) == 80f)
  }

  test("DSL distance, non spanning, non intersecting, top") {

    val r = Rectangle(
      x1 = 50, y1 = 150,
      x2 = 100, y2 = 140
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

  test("DSL distance, non spanning, non intersecting, top left") {

    val r = Rectangle(
      x1 = 0, y1 = 150,
      x2 = 10, y2 = 140
    )

    assert((centralRect distance r) == 80f)
    assert((r distance centralRect) == 80f)
  }

  test("DSL distance, non intersecting, left") {

    val r = Rectangle(
      x1 = 0, y1 = 100,
      x2 = 10, y2 = 50
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

  test("DSL distance, non spanning, non intersecting, left") {

    val r = Rectangle(
      x1 = 0, y1 = 100,
      x2 = 10, y2 = 50
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

  test("DSL distance, spanning, non intersecting, top") {

    val r = Rectangle(
      x1 = 0, y1 = 150,
      x2 = 150, y2 = 140
    )

    assert((centralRect distance r) == 40f)
    assert((r distance centralRect) == 40f)
  }

}
