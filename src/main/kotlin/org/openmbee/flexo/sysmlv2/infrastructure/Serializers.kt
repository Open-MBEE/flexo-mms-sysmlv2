package org.openmbee.flexo.sysmlv2.infrastructure

import com.google.gson.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.lang.Long
import java.lang.reflect.Type
import java.math.BigDecimal
import java.net.URI
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.util.*

object OffsetDateTimeSerializer : KSerializer<OffsetDateTime> {
    override val descriptor = PrimitiveSerialDescriptor("OffsetDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): OffsetDateTime {
        return OffsetDateTime.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        encoder.encodeString(value.toString())
    }
}
object URISerializer : KSerializer<URI> {
    override val descriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): URI {
        return URI.create(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: URI) {
        encoder.encodeString(value.toString())
    }
}
object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        return UUID.fromString(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}
object BigDecimalSerializer : KSerializer<BigDecimal> {
    override val descriptor = PrimitiveSerialDescriptor("BigDecimal", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): BigDecimal {
        return BigDecimal.valueOf(Long.getLong(decoder.decodeString()))
    }

    override fun serialize(encoder: Encoder, value: BigDecimal) {
        encoder.encodeString(value.toString())
    }
}

object OffsetDateTimeAdapter: JsonSerializer<OffsetDateTime>, JsonDeserializer<OffsetDateTime> {
    override fun serialize(offsetDateTime: OffsetDateTime?, p1: Type?, p2: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(offsetDateTime.toString())
    }

    override fun deserialize(json: JsonElement, p1: Type?, p2: JsonDeserializationContext?): OffsetDateTime {
        return OffsetDateTime.parse(json.asString)
    }
}

object LocalDateTimeAdapter: JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    override fun serialize(localDateTime: LocalDateTime?, p1: Type?, p2: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(localDateTime.toString())
    }

    override fun deserialize(json: JsonElement, p1: Type?, p2: JsonDeserializationContext?): LocalDateTime {
        return LocalDateTime.parse(json.asString)
    }
}

