package com.nes.myapprickymorti.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nes.myapprickymorti.data.remote.RetrofitInstance
import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.repository.CharacterRepository
import com.nes.myapprickymorti.data.repository.CharacterRepositoryImpl


import com.nes.myapprickymorti.domain.model.Response
import com.nes.myapprickymorti.domain.use_cases.characters.GetCharactersUseCase


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named



@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()



    @Provides
    fun provideApiService(): RickAndMortyApiService = RetrofitInstance.api

    @Provides
    fun provideCharacterRepository(
        apiService: RickAndMortyApiService
    ): CharacterRepository = CharacterRepositoryImpl(apiService)

    @Provides
    fun provideGetCharactersUseCase(
        repository: CharacterRepository
    ): GetCharactersUseCase = GetCharactersUseCase(repository)



}
