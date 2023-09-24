package com.jvktech.moviebuff.ui.screens.others

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import com.jvktech.moviebuff.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun UpdatesChannel(
    navigator: DestinationsNavigator
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Updates channel",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navigator.navigate(HomeScreenDestination) }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                }
            )
        },
        content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(MaterialTheme.spacing.medium)
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.small),
                        text = "26 September 2023",
                        style = MaterialTheme.typography.labelSmall
                    )
                }

                Card(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.medium),
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column {
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = painterResource(id = R.drawable.icc_wc),
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier.padding(MaterialTheme.spacing.medium),
                            text = "The ICC Men's Cricket World Cup is almost here! Access All Areas via our WhatsApp channel \uD83C\uDFCF",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

            }

        }
    )
}


//@Preview(showBackground = true)
//@Composable
//fun MyNewActivityPreview() {
//    UpdatesChannel()
//}