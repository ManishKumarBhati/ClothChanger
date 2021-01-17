package com.bmk.data.module

import android.util.Base64
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.text.Charsets.UTF_8

class EncodedStringTypeAdapter : TypeAdapter<EncodedString>() {
  override fun write(
    out: JsonWriter,
    value: EncodedString
  ) {
    out.value(Base64.encodeToString(value.toString().toByteArray(UTF_8), Base64.DEFAULT))
  }

  override fun read(reader: JsonReader): EncodedString? {
    return EncodedString(String(Base64.decode(reader.nextString(), Base64.DEFAULT)))
  }
}
