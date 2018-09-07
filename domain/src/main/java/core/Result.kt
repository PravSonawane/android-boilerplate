package core

/**
 * The [Result] of an operation.
 *
 * Intended to be used as return type of functions which perform CRUD (Create, Retrieve, Update
 * and Delete) operations.
 *
 * @see Create
 * @see Update
 * @see GetOne
 * @see GetList
 */
class Result {

  /**
   * The [Result] of a create operation.
   *
   * A create operation can result in one of the three results: successful creation
   * ([OnSuccess]), data already exists ([OnExists]) or an error ([OnError]).
   *
   * Note that [OnExists] might not be necessarily treated as an error.
   *
   * @see OnSuccess
   * @see OnExists
   * @see OnError
   */
  sealed class Create<out T> {

    /** Indicates that the creation operation was successful */
    class OnSuccess<out T>(val data: T) : Create<T>()

    /** Indicates that the data to be created already exists */
    class OnExists<out T>(val data: T): Create<T>()

    /** Indicates that the creation operation was unsuccessful */
    class OnError<out T>(val throwable: Throwable) : Create<T>()
  }

  /**
   * The [Result] of an update operation.
   *
   * An update operation can result in one of the two results: successful update
   * ([OnSuccess]) or an error ([OnError]).
   *
   * @see OnSuccess
   * @see OnError
   */
  sealed class Update<out T> {

    /** Indicates that the update operation was successful */
    class OnSuccess<out T>(val data: T) : Update<T>()

    /** Indicates that the update operation was unsuccessful */
    class OnError<out T>(val throwable: Throwable) : Update<T>()
  }

  /**
   * The [Result] of a retrieval operation of a single entity.
   *
   * A retrieval operation can result in one of the three results: successful retrieval
   * ([OnSuccess]), entity not found ([OnNotFound]) or an error ([OnError]).
   *
   * @see OnSuccess
   * @see OnNotFound
   * @see OnError
   */
  sealed class GetOne<out T> {

    /** Indicates that the retrieval operation was successful */
    class OnSuccess<out T>(val data: T) : GetOne<T>()

    /** Indicates that the entity was not found */
    class OnNotFound<out T>: GetOne<T>()

    /** Indicates that the retrieval operation was unsuccessful */
    class OnError<out T>(val throwable: Throwable) : GetOne<T>()
  }

  /**
   * The [Result] of a retrieval operation of a [List] of entities.
   *
   * A retrieval operation can result in one of the two results: successful update
   * ([OnSuccess]) or an error ([OnError]).
   *
   * @see OnSuccess
   * @see OnError
   */
  sealed class GetList<out T> {
    /** A successful result */
    class OnSuccess<out T>(val data: List<T>) : GetList<T>()

    /** An error */
    class OnError<out T>(val throwable: Throwable) : GetList<T>()
  }
}
