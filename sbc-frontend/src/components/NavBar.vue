<template>
    <div id="NavBar">
        <b-navbar toggleable="md" type="dark" variant="primary">
            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
            <b-navbar-brand tag="h1">sBC - Searching eBooks Collections</b-navbar-brand>

            <b-collapse is-nav id="nav-collapse">
                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-form><Settings/></b-nav-form>
                    <b-nav-form><About/></b-nav-form>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    import About from '@/components/About.vue'
    import Settings from '@/components/Settings.vue'
    
    export default {
        name: "NavBar",
        components: {
            About, Settings
        }
    }
</script>
