package org.openmbee.flexo.sysmlv2.infrastructure

import io.ktor.server.plugins.BadRequestException

private val VALID_ID_REGEX = Regex("^[a-zA-Z0-9_-]+$")

/**
 * Validate that an ID string contains only allowed characters: a-zA-Z0-9_-.
 * Throws [BadRequestException] (mapped to HTTP 400 by the global StatusPages handler)
 * if the ID is invalid.
 */
fun requireValidId(id: String, fieldName: String = "id") {
    if (!VALID_ID_REGEX.matches(id)) {
        throw BadRequestException("$fieldName must match [a-zA-Z0-9_-]+, got: '$id'")
    }
}

/**
 * Generate a new random ID string (UUID format, which is valid since hyphens are allowed).
 */
fun generateId(): String = java.util.UUID.randomUUID().toString()
