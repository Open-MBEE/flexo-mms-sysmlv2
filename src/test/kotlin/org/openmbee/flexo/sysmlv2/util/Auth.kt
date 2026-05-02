package org.openmbee.flexo.sysmlv2.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

/**
 * Test authentication helpers. The token is generated locally using the
 * same shared HMAC secret that the layer1-service test container is
 * configured with (see `src/test/resources/test.env` and
 * `application.test.conf`). No login service is involved.
 */
data class AuthStruct(
    val username: String = "user01",
    val groups: List<String> = listOf("super_admins")
)

val rootAuth = AuthStruct("root", listOf())
val superAdminAuth = AuthStruct("user01", listOf("super_admins"))
val anonAuth = AuthStruct("anon", listOf())

/**
 * Generate an `Authorization: Bearer <jwt>` header value for the given
 * test user. The token is signed with HMAC256 using the shared secret
 * from `application.test.conf` and is valid for one day.
 */
fun authorization(auth: AuthStruct = superAdminAuth): String {
    val config = testEnv()
    val audience = config.property("jwt.audience").getString()
    val issuer = config.property("jwt.domain").getString()
    val secret = config.property("jwt.secret").getString()
    val expires = Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)

    val token = JWT.create()
        .withAudience(audience)
        .withIssuer(issuer)
        .withClaim("username", auth.username)
        .withClaim("groups", auth.groups)
        .withExpiresAt(expires)
        .sign(Algorithm.HMAC256(secret))

    return "Bearer $token"
}
