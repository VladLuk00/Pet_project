package com.example.petproject.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.petproject.ProtoPref
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


object ProtoBufSerializer: Serializer<ProtoPref> {

    override val defaultValue: ProtoPref
        get() = ProtoPref.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ProtoPref {
        try {
            return ProtoPref.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ProtoPref, output: OutputStream) {
        t.writeTo(output)
    }
}