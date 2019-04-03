package initializationroutingservice.argumentresolver

import java.lang.reflect.Parameter

import com.mockitoscala.debug.argumentresolver.{HeaderParamsResolver, IRSArgumentResolver}
import org.mockito.{Mockito, MockitoSugar}
import org.springframework.core.MethodParameter
import org.scalatest.WordSpec
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer

class IRSArgumentResolverSpec extends WordSpec with MockitoSugar {

  val headerParamsResolverMock = mock[HeaderParamsResolver]
  val methodParameterMock = mock[MethodParameter]
  val irsArgumentResolver = new IRSArgumentResolver(headerParamsResolverMock)


  "supportsParameter" should {
    "return true if parameter type is of type Integer.class or String.class" in {
      doReturn(classOf[String], Nil: _*).when(methodParameterMock).getParameterType()
      var isParameterSupported: Boolean = irsArgumentResolver.supportsParameter(methodParameterMock)

      assert(isParameterSupported , "String type is supported")

      doReturn(classOf[Integer], Nil: _*).when(methodParameterMock).getParameterType
      isParameterSupported = irsArgumentResolver.supportsParameter(methodParameterMock)

      assert(isParameterSupported, "Integer type is supported")
    }
  }


  "supportsParameter" should {
    "return false if parameter type is NOT of type Integer.class or String.class" in {
      doReturn(classOf[Boolean], Nil: _*).when(methodParameterMock).getParameterType()
      val isParameterSupported: Boolean = irsArgumentResolver.supportsParameter(methodParameterMock)

      assert(!isParameterSupported , "Boolean type is not supported")
    }
  }

  "resolverArgument" should {
    "invoke resolve method if header parameter is not resolved" in {
      val parameter = mock[Parameter]

      doReturn(parameter, Nil: _*).when(methodParameterMock).getParameter
      doReturn("string", Nil: _*).when(parameter).getName
      doReturn(true, Nil: _*).when(headerParamsResolverMock).isResolved
      doReturn("string", Nil: _*).when(headerParamsResolverMock).resolve(mock[NativeWebRequest])


      irsArgumentResolver.resolveArgument(methodParameterMock, mock[ModelAndViewContainer], mock[NativeWebRequest],
        mock[WebDataBinderFactory])

      Mockito.verify(headerParamsResolverMock).resolve(mock[NativeWebRequest])
    }
  }
}
