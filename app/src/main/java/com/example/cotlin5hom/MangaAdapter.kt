package com.example.cotlin5hom

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cotlin5hom.databinding.ItemBinding

class MangaAdapter {

    class AnimeAdapter() : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(), Parcelable {

        private var anime = listOf<Manga>()

        constructor(parcel: Parcel) : this() {

        }

        fun setData(data: List<Manga>) {
            anime = data
        }

        class AnimeViewHolder(private val binding: ItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun onBind(manga: Manga) = with(binding) {
                mangaCover.setImageResource(manga.image)
                anime.text = manga.title
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): AnimeViewHolder {
            val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AnimeViewHolder(binding)
        }

        override fun onBindViewHolder(holder: AnimeAdapter.AnimeViewHolder, position: Int) {
            holder.onBind(anime[position])
        }

        override fun getItemCount(): Int = anime.size
        override fun writeToParcel(parcel: Parcel, flags: Int) {

        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<AnimeAdapter> {
            override fun createFromParcel(parcel: Parcel): AnimeAdapter {
                return AnimeAdapter(parcel)
            }

            override fun newArray(size: Int): Array<AnimeAdapter?> {
                return arrayOfNulls(size)
            }
        }
    }
}