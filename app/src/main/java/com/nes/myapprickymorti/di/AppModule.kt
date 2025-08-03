package com.nes.myapprickymorti.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nes.myapprickymorti.core.Constants.POSTS
import com.nes.myapprickymorti.core.Constants.USERS
import com.nes.myapprickymorti.data.local.dao.FavoriteCharacterDao
import com.nes.myapprickymorti.data.local.dao.FavoritesRepository
import com.nes.myapprickymorti.data.local.dao.ReadEpisodeDao
import com.nes.myapprickymorti.data.local.dao.ReadEpisodeRepository
import com.nes.myapprickymorti.data.remote.RetrofitInstance
import com.nes.myapprickymorti.data.remote.RickAndMortyApiService
import com.nes.myapprickymorti.data.repository.AuthRepositoryImpl
import com.nes.myapprickymorti.data.repository.CharacterRepositoryImpl
import com.nes.myapprickymorti.data.repository.UserRepositoryImpl
import com.nes.myapprickymorti.data.repository.PostRepositoryImpl


import com.nes.myapprickymorti.domain.repository.AuthRepository
import com.nes.myapprickymorti.domain.repository.CharacterRepository
import com.nes.myapprickymorti.domain.repository.PostsRepository
import com.nes.myapprickymorti.domain.repository.UsersRepository
import com.nes.myapprickymorti.domain.use_cases.auth.AuthUseCases
import com.nes.myapprickymorti.domain.use_cases.auth.GetCurrentUser
import com.nes.myapprickymorti.domain.use_cases.auth.Login
import com.nes.myapprickymorti.domain.use_cases.auth.SignUp
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharacterById
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharactersUseCase
import com.nes.myapprickymorti.domain.use_cases.posts.GetPosts
import com.nes.myapprickymorti.domain.use_cases.posts.PostsUseCases
import com.nes.myapprickymorti.domain.use_cases.users.Create
import com.nes.myapprickymorti.domain.use_cases.users.GetUserById
import com.nes.myapprickymorti.domain.use_cases.users.UsersUseCases
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharacters
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetCharactersByIds
import com.nes.myapprickymorti.domain.use_cases.getcharacters.GetEpisodesByIds


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named



@InstallIn(SingletonComponent::class) //Se instala en el componente Singleton
@Module //Indica que es un modulo Dagger
object AppModule {


    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()


    @Provides
    @Named(USERS)
    fun provideStorageUserRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun providesUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POSTS)

    @Provides
    @Named(POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // =========== Proveedor de servicios de red
    @Provides
    fun provideApiService(): RickAndMortyApiService = RetrofitInstance.api




    //============= Proveedor de Repositorios
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UserRepositoryImpl): UsersRepository = impl

    @Provides
    fun providePostsRepository(impl: PostRepositoryImpl): PostsRepository = impl

    @Provides
    fun provideCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository = impl

    @Provides
    fun provideFavoritesRepository(dao: FavoriteCharacterDao): FavoritesRepository {
        return FavoritesRepository(dao)
    }

    @Provides
    fun provideReadEpisodeRepository(dao: ReadEpisodeDao): ReadEpisodeRepository {
        return ReadEpisodeRepository(dao)
    }


    //============= Proveedor de Casos de Uso
    @Provides
    fun provideGetCharactersUseCase(repository: CharacterRepository) = GetCharactersUseCase(
        getCharacters = GetCharacters(repository),
        getCharacterById = GetCharacterById(repository),
        getEpisodesByIds = GetEpisodesByIds(repository),
        getCharactersByIds = GetCharactersByIds(repository)
    )

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        signUp = SignUp(repository)

    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )

    @Provides
    fun providePostsUsesCases(repository: PostsRepository) = PostsUseCases(
        getPosts = GetPosts(repository)
    )






}
