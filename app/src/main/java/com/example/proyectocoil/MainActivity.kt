package com.example.proyectocoil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.proyectocoil.ui.theme.ProyectoCoilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoCoilTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    Scaffold(
        topBar = { AppBar() },
        content = { padding ->
            PostList(modifier = Modifier.padding(padding))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text("ProyectoDarwinCoil") },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun PostList(modifier: Modifier = Modifier) {
    val posts = listOf(
        Post("DUKI", "https://www.canal26.com/media/image/2023/02/16/533149.jpg", "Rockstar life!"),
        Post("Penelope", "https://mezcalent.com/images/wm/400/galleries/2008/02/1067-PenelopeMenchaca_3428MEZ.jpg", "Show de hoy!")
    )

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { post ->
            PostCard(post)
        }
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = post.profileImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(post.username, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = post.profileImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { }) {
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Like")
                }
                IconButton(onClick = { }) {
                    Icon(Icons.Outlined.Share, contentDescription = "Share")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(post.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

data class Post(val username: String, val profileImageUrl: String, val description: String)

@Preview(showBackground = true)
@Composable
fun PreviewAppContent() {
    ProyectoCoilTheme {
        AppContent()
    }
}