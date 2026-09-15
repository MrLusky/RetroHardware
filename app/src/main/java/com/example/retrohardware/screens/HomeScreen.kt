package com.example.retrohardware.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.retrohardware.data.FirebaseRepository
import com.example.retrohardware.data.HardwareItem
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.retrohardware.R

@Composable
fun HomeScreen(
    onItemClick: (HardwareItem) -> Unit,
    onLogout: () -> Unit
) {

    val repository = remember {
        FirebaseRepository()
    }

    var hardwareItems by remember {
        mutableStateOf<List<HardwareItem>>(emptyList())
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        repository.getHardwareItems(

            onSuccess = { items ->
                hardwareItems = items
            },

            onError = { error ->
                errorMessage =
                    error.message ?: "Erro ao carregar catálogo."
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // CABEÇALHO
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = "RETRO",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "HARDWARE",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "História da Computação",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Button(
                onClick = onLogout
            ) {
                Text("Sair")
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // TÍTULO
        Text(
            text = "Linha do tempo",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Explore tecnologias que marcaram a evolução da computação.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ERRO
        if (errorMessage.isNotBlank()) {

            Text(
                text = errorMessage
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        // LISTA
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(hardwareItems) { item ->

                HardwareCard(
                    item = item,
                    onClick = {
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

@Composable
fun HardwareCard(
    item: HardwareItem,
    onClick: () -> Unit
) {

    val image = when (item.id) {
        "valvulas" -> R.drawable.valvulas
        "cartoes_perfurados" -> R.drawable.cartoes_perfurados
        "eniac" -> R.drawable.eniac
        "primeiro_microchip" -> R.drawable.primeiro_microchip
        else -> R.drawable.primeiro_microchip
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(20.dp)
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Image(
                painter = painterResource(id = image),
                contentDescription = item.nome,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = item.nome,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.ano,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // CATEGORIA
            AssistChip(
                onClick = onClick,
                label = {
                    Text(item.categoria)
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // DESCRIÇÃO
            Text(
                text = item.descricao,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // VER DETALHES
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "VER DETALHES",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Ver detalhes"
                )
            }
        }
    }
}