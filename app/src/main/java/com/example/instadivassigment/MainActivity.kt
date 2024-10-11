package com.example.instadivassigment

import TagViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instadivassigment.Screens.NextScreen
import com.example.instadivassigment.Screens.TagsScreen
import com.example.instadivassigment.ui.theme.InstaDivAssigmentTheme

class MainActivity : ComponentActivity() {
    private val repository = TagRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaDivAssigmentTheme {
                val viewModel = TagViewModel(repository)
                TagNavHost(viewModel = viewModel)

            }
        }
    }
}

@Composable
fun TagNavHost(viewModel: TagViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "tagsScreen") {
        composable("tagsScreen") {
            TagsScreen(viewModel, navController)
        }
        composable(
            "nextScreen/{selectedTag}",
            arguments = listOf(navArgument("selectedTag") { type = NavType.StringType })
        ) { backStackEntry ->
            val selectedTag = backStackEntry.arguments?.getString("selectedTag") ?: ""
            NextScreen(selectedTag)
        }
    }
}


