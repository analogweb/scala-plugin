package org.analogweb.scala

import java.lang.annotation.Annotation
import org.analogweb.InvocationMetadata
import org.analogweb.MediaType
import org.analogweb.RequestContext
import org.analogweb.TypeMapper
import org.analogweb.core.MediaTypes
import org.analogweb.core.SpecificMediaTypeRequestValueResolver
import org.analogweb.core.ParameterValueResolver
import org.analogweb.core.PathVariableValueResolver
import org.analogweb.core.CookieValueResolver
import org.analogweb.core.RequestBodyValueResolver
import org.analogweb.core.XmlValueResolver
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

trait Resolvers {

  protected def parameter = classOf[ParameterValueResolver]

  protected def path = classOf[PathVariableValueResolver]

  protected def cookie = classOf[CookieValueResolver]

  protected def body = classOf[RequestBodyValueResolver]

  protected def xml = classOf[XmlValueResolver]

  protected def json = classOf[ScalaJacksonJsonValueResolver]

}

class ScalaJacksonJsonValueResolver extends SpecificMediaTypeRequestValueResolver {

  protected val objectMapper: ObjectMapper = {
    val m = new ObjectMapper
    m.registerModule(DefaultScalaModule)
    m
  }

  override def resolveValue(request: RequestContext, metadata: InvocationMetadata, key: String, requiredType: Class[_], annoattions: Array[Annotation]): AnyRef = objectMapper.readValue(request.getRequestBody, requiredType).asInstanceOf[AnyRef]

  override def supports(contentType: MediaType) = MediaTypes.APPLICATION_JSON_TYPE.isCompatible(contentType);

}