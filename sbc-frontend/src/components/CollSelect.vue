<template>
    <div>
        <b-input-group>
            <div class="w-20"><b-input-group-text slot="prepend">Collection</b-input-group-text></div>
            <b-form-select v-model="currcoll" :options="collections"></b-form-select>
            <div class="w-10"><b-button block squared @click="browse">Browse</b-button></div>
        </b-input-group>
<!-- For debugging
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Name</th>
                <th>Path</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="coll in collections" :key="coll.text">
                <td>{{ coll.text }}</td>
                <td>{{ coll.value }}</td>
            </tr>
            </tbody>
        </table>

        <div class="mt-3">Selected: <strong>{{ currcoll }}</strong></div>
        -->
    </div>
</template>

<script>
    import {baseUrl} from '@/store/index.js'

    export default {
        name: 'CollSelect',
        computed: {
            collections: {
                get() {
                    return this.$store.state.collections;
                }
            },
            currcoll : {
                get() {
                    return this.$store.state.currcoll;
                },
                set(value) {
                    this.$store.commit('setCurrColl', value);
                }
            }
        },
        methods: {
            browse() {
                window.open(baseUrl + "/" + this.collections.find(it => it.value === this.currcoll).text + "/ebc-index.html", "_blank");
            }
        }
    }
</script>   

<style scoped>
    .w-20 {width: 20%;}
    .w-10 {width: 10%;}
</style>