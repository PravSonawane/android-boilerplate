package model.foo


data class Foo(
    val id: String,
    val name: String,
    val dataState: State = State.DRAFT
) {

  enum class State {
    DRAFT,
    ACTIVE
  }
}