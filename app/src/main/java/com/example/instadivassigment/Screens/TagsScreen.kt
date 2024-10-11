package com.example.instadivassigment.Screens

import TagViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instadivassigment.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsScreen(viewModel: TagViewModel, navController: NavController) {
    // Collect the tags from the ViewModel
    val tags by viewModel.allTags.collectAsState()

    // Use rememberSaveable to preserve the selected tag across configuration changes
    var currentlySelectedTag by rememberSaveable { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = colorResource(id = R.color.screenBgColor)
    ){ innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                StaggeredFlowRow() {
                    tags.forEach { tag ->
                        val isSelected = tag == currentlySelectedTag
                        TagItem(
                            tag = tag,
                            isSelected = isSelected,
                            onClick = {
                                currentlySelectedTag = tag
                            }
                        )
                    }
                }



                Spacer(modifier = Modifier.height(24.dp))

                // Submit button
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(!currentlySelectedTag.isNullOrEmpty()) colorResource(id = R.color.midLightBlue)
                        else Color.Transparent
                        ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        currentlySelectedTag?.let {
                            navController.navigate("nextScreen/$it")
                        }
                    },
                    enabled = currentlySelectedTag != null
                ) {
                    Text(
                        text = "Submit",
                        color = if(!currentlySelectedTag.isNullOrEmpty()) Color.White else Color.Transparent,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun TagItem(tag: String, isSelected: Boolean, onClick: () -> Unit) {
    val purpleShades = listOf(
        colorResource(id = R.color.lightPurple),
        colorResource(id = R.color.midPurple),
    )
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp),
        shape = RoundedCornerShape(50),
        color = if (isSelected) Color.Blue else purpleShades.random(),
        contentColor = Color.White
    ) {
        Text(
            text = tag,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}
