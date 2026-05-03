package org.openmbee.flexo.sysmlv2.infrastructure

private val VALID_ID_REGEX = Regex("^[a-zA-Z0-9_-]+$")

/**
 * Validate that an ID string contains only allowed characters: a-zA-Z0-9_-
 * @throws IllegalArgumentException if the ID is invalid
 */
fun requireValidId(id: String, fieldName: String = "id") {
    require(VALID_ID_REGEX.matches(id)) {
        "$fieldName must match [a-zA-Z0-9_-]+, got: '$id'"
    }
}

/**
 * Generate a new random ID string (UUID format, which is valid since hyphens are allowed).
 */
fun generateId(): String = java.util.UUID.randomUUID().toString()
