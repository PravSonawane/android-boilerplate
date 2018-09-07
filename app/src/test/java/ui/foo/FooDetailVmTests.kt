package ui.foo

import android.app.Application
import android.content.res.Resources
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.mock

class FooDetailVmTests {

  lateinit var mockApplication: Application
  lateinit var mockResources: Resources

  @Before
  fun before() {
    mockApplication = mock(Application::class.java)
    mockResources = mock(Resources::class.java)
    `when`(mockApplication.resources).thenReturn(mockResources)
  }

  @Test
  fun `test`() {
    `when`(mockResources.getString(anyInt()))
  }
}