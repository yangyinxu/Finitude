package com.yangyinxu.finitude.presentation.player

import android.app.Application
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.yangyinxu.finitude.util.Constants.PLAYER_CONTENT_URI_SCHEME

data class ContentMetaData(
    val fileName: String
)

interface MetadataReader {
    // a nullable Metadata is needed to handle cases where
    //  uri is not a content uri (e.g. file/resource uri)
    fun getMetadataFromUri(contentUri: Uri): ContentMetaData?
}

class MetadataReaderImpl(
    private val app: Application
): MetadataReader {

    override fun getMetadataFromUri(contentUri: Uri): ContentMetaData? {
        if (contentUri.scheme != PLAYER_CONTENT_URI_SCHEME) {
            Log.w("MetadataReader", "Provided uri is not a content uri")
            return null
        }
        val fileName = app.contentResolver
            .query(
                contentUri,
                arrayOf(MediaStore.Video.VideoColumns.DISPLAY_NAME),
                null,
                null,
                null
            )
            ?.use { cursor ->
                // `use` creates a cursor to collect the results of this
                //  query
                // `index` refers to the display name column of the row
                val index = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME)
                // move to the first row of the result
                cursor.moveToFirst()
                // saves to filename
                cursor.getString(index)
            }
        return fileName?.let { fullFileName ->
            ContentMetaData(
                // lastPathSegment refers to the file type (e.g. `.mp4`)
                fileName = Uri.parse(fullFileName).lastPathSegment ?: return null
            )
        }
    }
}