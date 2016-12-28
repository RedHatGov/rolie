package gov.nist.rolie.polie.core.models.elements;


/**
 * 8.3.4 The "app:accept" Element

The content of an "app:accept" element value is a media range as defined in [RFC2616]. The media range specifies a type of representation that can be POSTed to a Collection.

The app:accept element is similar to the HTTP Accept request-header [RFC2616]. Media type parameters are allowed within app:accept, but app:accept has no notion of preference -- "accept-params" or "q" arguments, as specified in Section 14.1 of [RFC2616] are not significant.

White space (as defined in [REC-xml]) around the app:accept element's media range is insignificant and MUST be ignored.

A value of "application/atom+xml;type=entry" MAY appear in any app:accept list of media ranges and indicates that Atom Entry Documents can be POSTed to the Collection. If no app:accept element is present, clients SHOULD treat this as equivalent to an app:accept element with the content "application/atom+xml;type=entry".

If one app:accept element exists and is empty, clients SHOULD assume that the Collection does not support the creation of new Entries.

appAccept =
   element app:accept {
         appCommonAttributes,
         ( text? )
   }
 * @author sab3
 *
 */
public class APPAccept implements APPElement{

}
