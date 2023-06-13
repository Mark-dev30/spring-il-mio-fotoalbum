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
            this.message.email = ""
            this.message.message = ""
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
                <input type="text" class="form-control mb-2" placeholder="Title" v-model="this.title">
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
        <div class="row justify-content-center mt-5">
            <div class="col-6">
                <h1>Send Message</h1>
                <form @submit.prevent="sendMessage()">
                    <div class="mb-3">
                        <label class="form-label">email</label>
                        <input class="form-control" type="email" name="email" v-model="message.email">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Text</label>
                        <input class="form-control" type="text" name="message" v-model="message.message">
                    </div>
                    <input type="submit" value="CREATE" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card-img-top {
    width: 100%;
    height: 15vw;
    object-fit: cover;
}
</style>
