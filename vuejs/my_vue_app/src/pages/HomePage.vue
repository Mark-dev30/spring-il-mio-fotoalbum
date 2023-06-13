<script >
import axios from "axios";
export default {
    name: 'HomePage',
    data() {
        return {
            title: "",
            urlimages: "http://localhost:8080/api/v1/",
            images: [],
            message: {
                email: "",
                message: ""
            }

        }
    },
    methods: {
        getImages() {
            axios.get(this.urlimages + "images").then(res => {
                this.images = res.data;;
            }).catch(err => console.log(err));
        },
        searchImages() {

            axios.get(this.urlimages + "images?title=" + this.title)
                .then(res => {
                    this.images = res.data;
                }).catch(err => console.log(err));

        },
        sendMessage() {
            console.log(JSON.stringify(this.message));
            axios.post(this.urlimages + "message", this.message).then(res => {
            }).catch(err => console.log(err));
        }
    },
    mounted() {
        this.getImages();
    },

}
</script>

<template>
    <div class="container-fluid">
        <div class="row mt-3">
            <h1>Images:</h1>
            <div class="col-6">
                <input type="text" class="form-control" placeholder="Title" v-model="this.title">
                <button @click="searchImages()" class="btn btn-primary">Search</button>
            </div>
            <div class="row">
                <div class="col-3 mt-3" v-for="image in images">
                    <div class="card" style="width: 18rem;">
                        <img :src="image.url" class="card-img-top" :alt="image.title">
                        <div class="card-body">
                            <h5 class="card-title">{{ image.title }}</h5>
                            <p class="card-text text-truncate">{{ image.description }}.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--     <h1>Images:</h1>
    <div>
        <input type="text" v-model="this.title">
        <button @click="searchImages()">Search</button>
        <ul>
            <li v-for="image in images">
                {{ image.title }}
            </li>
        </ul>
    </div>
    <h1>Send Message</h1>
    <form @submit.prevent="sendMessage()">
        <div>
            <label>email</label>
            <input type="email" name="email" v-model="message.email">
        </div>
        <div>
            <label>Text</label>
            <input type="text" name="message" v-model="message.message">
        </div>
        <input type="submit" value="CREATE">
    </form> -->
</template>

<style scoped>
.card-img-top {
    width: 100%;
    height: 15vw;
    object-fit: cover;
}
</style>
