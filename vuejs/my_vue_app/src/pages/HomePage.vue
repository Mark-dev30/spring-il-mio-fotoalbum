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
    <h1>Images:</h1>
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
    </form>
</template>

<style scoped></style>
