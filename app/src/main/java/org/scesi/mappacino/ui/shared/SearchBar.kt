package org.scesi.mappacino.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.scesi.mappacino.domain.ClassroomLocationUI
import org.scesi.mappacino.domain.ISearchable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DockedSearchBarComponent(
    searchQuery: String,
    updateQuery: (String) -> Unit,
    searchList: List<ISearchable>,
    onItemClick: (location: ClassroomLocationUI) -> Unit
) {
    var active by rememberSaveable { mutableStateOf(false) }

    DockedSearchBar(
        query = searchQuery,
        onQueryChange = { updateQuery(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Escribe tu aula") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) }

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(searchList) { item ->
                Text(text = item.name, modifier = Modifier.clickable {
                    active = false
                    onItemClick(item as ClassroomLocationUI)
                })
            }
        }
    }
}
