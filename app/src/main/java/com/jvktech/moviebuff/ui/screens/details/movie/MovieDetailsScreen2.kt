package com.jvktech.moviebuff.ui.screens.details.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen2(

) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Icc World cup",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .height(250.dp)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icc_wc),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                // Poster Image
                Box(
                    modifier = Modifier
                        .size(width = 80.dp, height = 110.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_demo_poster),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Title Text
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.Bottom)
                ) {
                    Text(
                        text = "Leo",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    // Certification, Release Date, and Runtime in a Row scope
                    Row {
                        Text(
                            text = "U/A",
                            fontSize = 14.sp, fontWeight = FontWeight.Bold,
                            color = Color.White, modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "Apr 2021",
                            fontSize = 14.sp, fontWeight = FontWeight.Bold,
                            color = Color.White, modifier = Modifier.padding(horizontal = 4.dp)
                        )
                        Text(
                            text = "\u00B7", // Unicode for middle dot (·)
                            fontSize = 14.sp,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )

                        Text(
                            text = "124 Mins",
                            fontSize = 14.sp, fontWeight = FontWeight.Bold,
                            color = Color.White, modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }


    }
}

@Composable
@Preview
fun MovieDetailsScreen2Preview() {
    MovieDetailsScreen2()
}