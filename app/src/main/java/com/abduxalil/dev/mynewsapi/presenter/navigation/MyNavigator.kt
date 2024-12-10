package com.abduxalil.dev.mynewsapi.presenter.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abduxalil.dev.mynewsapi.domain.entity.NewsRepo
import com.abduxalil.dev.mynewsapi.presenter.NewsRepoScreen
import com.abduxalil.dev.mynewsapi.presenter.RepoDetailScreen

@Composable
fun MyNavigator() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "newsRepoScreen") {
        composable("newsRepoScreen") {
            NewsRepoScreen(navController = navController)
        }

        composable("repoDetail/{repoDescription}/{repoId}/{repoUrl}") { backStackEntry ->
            val repoDescription = backStackEntry.arguments?.getString("repoDescription")
            val repoId = backStackEntry.arguments?.getString("repoId")
            val repoUrl = backStackEntry.arguments?.getString("repoUrl")

            RepoDetailScreen(
                repoId = repoId,
                repoDescription = repoDescription,
//                repoUrl = repoUrl,
                navController = navController,
//                repo = NewsRepo(
//                    name = "", description = repoDescription.orEmpty(), id = repoId.orEmpty(), url = repoUrl.orEmpty()
//                )
            )
        }
    }
}