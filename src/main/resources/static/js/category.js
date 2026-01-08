document.addEventListener('alpine:init', () => {
    Alpine.store('categoryStore', {

        categories: [],

        async init() {
            await this.refresh();
        },

        async refresh() {
            let res = await fetch('/api/categories');
            let json = await res.json();
            this.categories = [this.blank(), ...json];
        },

        blank() {
            return {
                id: null,
                name: '',
                description: '',
                details: '',
                position: null,
            };
        },

        async createCategory(category) {
            await fetch(`/api/categories`, {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(category)
            });
            await this.refresh();
        },

        async moveUp(category) {
            await fetch(`/api/categories/${category.id}/position`, {
                method: 'PATCH',
                headers: getHeaders(),
            });
            await this.refresh();
        },

        async updateCategory(category) {
            await fetch(`/api/categories/${category.id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify(category)
            });
            await this.refresh();
        },

        async deleteCategory(id) {
            if (confirm("Are you sure you want to delete this category?")) {
                await fetch(`/api/categories/${id}`, {
                    method: 'DELETE',
                    headers: getHeaders(),
                });
                await this.refresh();
            }
        },

        async uploadImage(image, categoryId) {
            const formData = new FormData();
            formData.append('image', image);
            formData.append('categoryId', categoryId);

            const headers = getHeaders();
            delete headers['Content-Type'];

            await fetch(`/api/images/category`, {
                method: 'POST',
                headers: headers,
                body: formData,
            });
            await this.refresh();
        },

        async deleteImage(id) {
            console.log(id);
            await fetch(`/api/images/${id}`, {
                method: 'DELETE',
                headers: getHeaders(), 
            });
            await this.refresh();
        },

    });
});

