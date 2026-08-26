package com.example.retrohardware.data

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getHardwareItems(
        onSuccess: (List<HardwareItem>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection("hardware")
            .get()
            .addOnSuccessListener { result ->

                val items = result.documents.mapNotNull { document ->
                    document.toObject(HardwareItem::class.java)?.copy(
                        id = document.id
                    )
                }

                onSuccess(items)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }
}