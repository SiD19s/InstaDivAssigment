package com.example.instadivassigment

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class TagRepository {
    private val db = FirebaseFirestore.getInstance()

    // Suspend function to handle async fetching
    suspend fun fetchTags(): List<String> {
        return try {
            val documentSnapshot = db.collection("strings")
                .document("BjTjT76PYtphfP7S4cWD")
                .get()
                .await()

            if (documentSnapshot.exists()) {
                // Fetch the list of strings from Firestore
                val fetchedData: List<String>? = documentSnapshot.get("strings") as? List<String>
                fetchedData ?: emptyList()
            } else {
                Log.w("FirestoreData", "Document does not exist")
                emptyList()
            }
        } catch (e: Exception) {
            Log.w("FirestoreData", "Error fetching data", e)
            emptyList()
        }
    }
}