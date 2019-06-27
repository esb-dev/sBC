<template>
    <div id="App">
        <b-container>
            <NavBar/>
            <CollSelect/>
            <SearchBar/>
            <ResultList/>
        </b-container>
    </div>
</template>

<script>
    import NavBar from './components/NavBar.vue'
    import CollSelect from './components/CollSelect.vue'
    import SearchBar from './components/SearchBar.vue'
    import ResultList from './components/ResultList.vue'

    export default {
        name: 'App',
        components: {
            NavBar, CollSelect, SearchBar, ResultList
        },
        mounted() {
            this.$store.dispatch('loadCollections');
            if (localStorage.getItem('settings')) 
                this.$store.dispatch('loadSettings', JSON.parse(localStorage.getItem('settings')));
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
