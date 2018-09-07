package ui.helpers

import model.foo.Foo

internal fun createDummyFoo(
  id: String
): Foo {
  return Foo(
      id, "Foo $id"
  )
}