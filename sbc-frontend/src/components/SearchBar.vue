<template>
    <div>
        <b-input-group>
            <div class="w-20"><b-input-group-text squared slot="prepend">Search Criteria</b-input-group-text></div>
            <b-form-input v-model="searchcrit" placeholder="Enter search criteria"></b-form-input>
            <div class="w-10"><b-button block squared @click="search" :disabled="isDisabled">Search</b-button></div>
        </b-input-group>

    </div>
</template>

<script>
    export default {
        name: "SearchBar",
        data: function () {
            return {
                searchcrit: ""
            }
        },
        computed: {
            isDisabled() { 
                return this.searchcrit.trim() == "";
            },
            currname: {
                get() {
                    return this.$store.state.collections[this.$store.state.currcoll].text;
                }
            }
        },
        methods: {
            search() {
                // build request
                let request = "coll=" + this.currname + "&query=" + this.searchcrit.trim() + "&num=" + this.$store.state.settings.num;
                this.$store.dispatch('searchBooks', request);
                // eslint-disable-next-line 
                console.log(this.$store.state.results);
            }
        }
    }
</script>

<style scoped>
    .w-20 {width: 20%;}
    .w-10 {width: 10%;}
</style>