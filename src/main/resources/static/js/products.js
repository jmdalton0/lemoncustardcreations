document.addEventListener('alpine:init', () => {
    Alpine.data('productStore', () => ({

        products: [],

        async init() {
            await this.refresh();
        },

        async refresh() {
            let res = await fetch('/api/products');
            let json = await res.json();
            this.products = [this.blank(), ...json];
        },

        blank() {
            return {
                id: null,
                name: '',
                price: 0,
                position: null,
            };
        },

        async createProduct(product) {
            await fetch(`/api/products`, {
                method: 'POST',
                headers: getHeaders(),
                body: JSON.stringify(product)
            });
            await this.refresh();
        },

        async moveUp(product) {
            await fetch(`/api/products/${product.id}/position`, {
                method: 'PATCH',
                headers: getHeaders(),
            });
            await this.refresh();
        },

        async updateProduct(product) {
            await fetch(`/api/products/${product.id}`, {
                method: 'PUT',
                headers: getHeaders(),
                body: JSON.stringify(product)
            });
            await this.refresh();
        },

        async deleteProduct(id) {
            if (confirm("Are you sure you want to delete this product?")) {
                await fetch(`/api/products/${id}`, {
                    method: 'DELETE',
                    headers: getHeaders()
                });
                await this.refresh();
            }
        },

        async uploadImage(image, productId) {
            const formData = new FormData();
            formData.append('image', image);
            formData.append('productId', productId);

            const headers = getHeaders();
            delete headers['Content-Type'];

            await fetch(`/api/images/product`, {
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

    }))
});

